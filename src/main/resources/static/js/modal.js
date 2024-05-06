import {bindClickEventOnElement} from "./calendar.js";
import {saveLimitMoney} from "./ajax.js";

const INDEX_MODAL = document.getElementsByClassName("modal")[0];

var observer = new MutationObserver(function(event){

    //모달 창의 변경이 감지된 상태에서
    //닫힌 상태일 때
    if(!$(INDEX_MODAL).hasClass('show')){
        //닫았을 때
        // ...
    }
})

observer.observe(INDEX_MODAL, {
    attributes : true,
    attributeFilter: ['class'],
    childList: false,
    characterData: false
})

export function activeModal(){
    console.log("activeModal............")
    $('.modal').modal('show');
}
export function closeModal(){
    $('.modal').modal('toggle');
}

let input = document.getElementsByClassName("limit_money")[0];
let feedback = document.getElementsByClassName("feedback")[0];

function afterValidCheck(inputClsName, feedbackClsName, feedbackText){
    console.log(input.className);
    input.className = inputClsName;
    feedback.className = feedbackClsName;
    feedback.innerText = feedbackText
}
function isValid(){
    let value = input.value;
    let clsName = "form-control limit_money"

    let valid = clsName + " " + "is-valid";
    let invalid = clsName + " " + "is-invalid";
    let feedback_invalid = "invalid-feedback";
    let feedback_valid = "valid-feedback";


    if(value == ''){
        afterValidCheck(invalid, feedback_invalid, "you must input limit money")
        return false;
    }
    if( !(/^[0-9]+$/.test(value)) ){
        afterValidCheck(invalid, feedback_invalid, "you must input numbers")
        return false;
    }
    if(Number(value) < 50000){
        afterValidCheck(invalid, feedback_invalid, "you must input money over 50000")
        return false;
    }

    afterValidCheck(valid, feedback_valid, "Good!");
    return true;
}
export function setAjax(){
    bindClickEventOnElement('.modal-submit', function(e){
        if(isValid()) {
            saveLimitMoney(input.value)
            input.value = " ";
        }
    })
}

function main(){
    setAjax();
}

main();