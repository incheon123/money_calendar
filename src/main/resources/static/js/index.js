import { clickPrevNextBtn } from "./module.js";

function clickForPrevCalendar(){
    clickPrevNextBtn('.pre-month')
}

function clickForNextCalendar(){
    clickPrevNextBtn('.next-month')
}

export function executeCalendarBtn(){
    console.log("index + executeCalendarBtn")
    clickForNextCalendar();
    clickForPrevCalendar();
}

executeCalendarBtn();