package com.ktg.framework.websocket.group;

import com.ktg.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.Session;
import java.io.IOException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * websocket 客户端组合集
 *
 * @author qiushile <qiushile@sina.com>
 * @date 2023/11/7
 */
public class WebSocketGroups {
    /**
     * WebSocketUsers 日志控制器
     */
    private static final Logger logger = LoggerFactory.getLogger(WebSocketGroups.class);

    /**
     * 节点集
     */
    private static Map<String, Session> NODES = new ConcurrentHashMap<>();

    /**
     * 组集
     */
    private static Map<String, Set<Session>> GROUPS = new ConcurrentHashMap<>();

    /**
     * 属集
     */
    private static Map<Session, String> BELONGS = new ConcurrentHashMap<>();


    /**
     * 存储节点
     *
     * @param group   组
     * @param session 节点信息
     */
    public static void put(String group, Session session) {
        NODES.put(session.getId(), session);
        GROUPS.computeIfAbsent(group, x -> new HashSet<>()).add(session);
        BELONGS.put(session, group);
    }

    /**
     * 移除节点
     *
     * @param session 节点信息
     * @return 移除结果
     */
    public static boolean remove(Session session) {
        return !BELONGS.containsKey(session) || remove(session.getId());
    }

    /**
     * 移除节点
     *
     * @param key 键
     */
    public static boolean remove(String key) {
        logger.info("\n 正在移除节点 - {}", key);
        Session session = NODES.get(key);
        String group = BELONGS.remove(session);
        GROUPS.get(group).remove(session);
        Session remove = NODES.remove(key);
        if (StringUtils.isEmpty(GROUPS.get(group))) {
            GROUPS.remove(group);
        }
        if (remove != null) {
            boolean containsValue = NODES.containsValue(remove);
            logger.info("\n 移除结果 - {}", containsValue ? "失败" : "成功");
            return containsValue;
        } else {
            return true;
        }
    }

    /**
     * 获取在线节点列表
     *
     * @return 返回节点集合
     */
    public static Map<String, Session> getNodes() {
        return NODES;
    }

    /**
     * 群发消息文本消息
     *
     * @param message 消息内容
     */
    public static void sendMessageToGroupByText(String group, String message) {
        GROUPS.get(group).forEach(session -> {
            sendMessageToNodeByText(session, message);
        });
    }

    /**
     * 群发消息文本消息
     *
     * @param message 消息内容
     */
    public static void sendMessageToNodesByText(String message) {
        NODES.values().forEach(session -> sendMessageToNodeByText(session, message));
    }

    /**
     * 发送文本消息
     *
     * @param session 自己的节点名
     * @param message 消息内容
     */
    public static void sendMessageToNodeByText(Session session, String message) {
        if (session != null) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.error("\n[发送消息异常]", e);
            }
        } else {
            logger.info("\n[你已离线]");
        }
    }

    public static void sendMesssageToNodeByName(String node, String message) {
        Session session = NODES.get(node);
        if (StringUtils.isNotNull(session)) {
            try {
                session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                logger.error("\n[发送消息异常]", e);
            }
        }
    }
}
