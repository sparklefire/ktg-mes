package com.ktg.mes.cal.controller;

import cn.hutool.core.collection.CollUtil;
import com.ktg.common.constant.UserConstants;
import com.ktg.common.core.controller.BaseController;
import com.ktg.common.core.domain.AjaxResult;
import com.ktg.common.utils.StringUtils;
import com.ktg.mes.cal.domain.CalCalendar;
import com.ktg.mes.cal.domain.CalHoliday;
import com.ktg.mes.cal.service.ICalCalendarService;
import com.ktg.mes.cal.service.ICalHolidayService;
import com.ktg.mes.cal.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 排班日历
 *
 * @author yinjinlu
 * @date 2022-06-10
 */
@RestController
@RequestMapping("/mes/cal/calendar")
public class CalCalendarController extends BaseController {

    @Autowired
    private ICalCalendarService calCalendarService;

    @Autowired
    private ICalHolidayService calHolidayService;

    @PreAuthorize("@ss.hasPermi('mes:cal:calendar:list')")
    @GetMapping("/list")
    public AjaxResult getCalendars(CalCalendar calCalendar){

        Date day = calCalendar.getDate();
        List<CalCalendar> days = null;
        if(StringUtils.isNull(day)){
            day = new Date();
        }

        if(UserConstants.CAL_QUERY_BY_TYPE.equals(calCalendar.getQueryType())){
            days=calCalendarService.getCalendarByType(day,calCalendar.getCalendarType());
        }else if(UserConstants.CAL_QUERY_BY_TEAM.equals(calCalendar.getQueryType())){
            days=calCalendarService.getCalendarByTeam(day,calCalendar.getTeamId());
        }else {
            days=calCalendarService.getCalendarByUser(day,calCalendar.getUserId());
        }
        return AjaxResult.success(getCalendarsWithoutHoliday(days));
    }


    /**
     * 过滤掉节假日
     * @param days
     * @return
     */
    private List<CalCalendar> getCalendarsWithoutHoliday(List<CalCalendar> days){
        CalHoliday param = new CalHoliday();
        List<CalHoliday> holidays = calHolidayService.selectCalHolidayList(param);
        if(CollUtil.isNotEmpty(holidays)){
            List<CalCalendar> results = days.stream().filter(
                    calCalendar -> holidays.stream().filter(calHoliday -> calCalendar.getTheDay().equals(CalendarUtil.getDateStr(calHoliday.getTheDay()))).collect(Collectors.toList()).size()==0
            ).collect(Collectors.toList());
            return results;
        }
        return days;
    }
}
