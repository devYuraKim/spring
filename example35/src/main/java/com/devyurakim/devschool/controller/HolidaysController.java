package com.devyurakim.devschool.controller;

import com.devyurakim.devschool.model.Holiday;
import com.devyurakim.devschool.repository.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.StreamSupport;

@Controller
public class HolidaysController {

    @Autowired
    private HolidaysRepository holidaysRepository;

    @GetMapping("/holidays/{display}")
    public String displayHolidays(Model model, @PathVariable String display){

        if(null != display && display.equals("all")){
            model.addAttribute("festival",true);
            model.addAttribute("federal",true);
        }else if(null != display && display.equals("federal")){
            model.addAttribute("federal",true);
        }else if(null != display && display.equals("festival")){
            model.addAttribute("festival",true);
        }

//        List<Holiday> holidays = Arrays.asList(
//                new Holiday(" Jan 1 ","New Year's Day", Holiday.Type.FESTIVAL),
//                new Holiday(" Oct 31 ","Halloween", Holiday.Type.FESTIVAL),
//                new Holiday(" Nov 24 ","Thanksgiving Day", Holiday.Type.FESTIVAL),
//                new Holiday(" Dec 25 ","Christmas", Holiday.Type.FESTIVAL),
//                new Holiday(" Jan 17 ","Martin Luther King Day", Holiday.Type.FEDERAL),
//                new Holiday(" July 4 ","Independence Day", Holiday.Type.FEDERAL),
//                new Holiday(" Sep 5 ","Labor Day", Holiday.Type.FEDERAL),
//                new Holiday(" Nov 11 ","Veterans Day", Holiday.Type.FEDERAL)
//        );

//        List<Holiday> holidays = holidaysRepository.findAllHolidays();
        Iterable<Holiday> holidays = holidaysRepository.findAll();
        //Iterable: general collection type > Collection > List, Set
        //Stream: a separate hierarchy
        List<Holiday> holidayList = StreamSupport.stream(holidays.spliterator(), false).toList();
        //StreamSupport: provides methods to create streams from various sources
        //spliterator()는 Iterable를 Spliterator로 반환, StreamSupport.stream()는 Spliterator를 stream으로 변환
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types){
            /**위의 holidays list로부터 model attibute name이 각각 FESTIVAL, FEDERAL이고, value가 element가 holiday인 list가 만들어짐*/
            model.addAttribute(type.toString(),
//                    (holidays.stream().filter(holiday -> holiday.getType().equals(type))).collect(Collectors.toList())
                    (holidayList.stream().filter(holiday->holiday.getType().equals(type)).toList())
            );
        }
        return "holidays.html";
    }


}
