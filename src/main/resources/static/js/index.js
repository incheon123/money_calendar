import { clickPrevNextBtn } from "./module.js";

let BUTTON_PREVIOUS_MONTH = '.pre-month';
let BUTTON_NEXT_MONTH     = '.next-month';

/**
 * 달력 상단에 있는 저번 달, 다음 달 버튼에 이벤트 할당
 */
function clickCalendarMonthBtn(){
    clickPrevNextBtn(BUTTON_PREVIOUS_MONTH, BUTTON_NEXT_MONTH);
}

clickCalendarMonthBtn();