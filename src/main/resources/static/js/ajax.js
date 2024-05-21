import {empty, emptyActualDate, updateHistoryContainer} from "./module.js";
import {insertHistorys, insertHistoryToCalendar, setLimitMoney} from "./getHistorys.js";
import {getCurrentDate, bindClickEventOnElement, emptyMoney} from "./calendar.js";
import {activeModal, closeModal} from "./modal.js";
/**
 * 특정 날짜의 내용을 수정하면 실행
 * @param requestObject
 */
export function updateHistorys(requestObject){
    console.log("updateHistorys......")
    $.ajax({
        url: '/money_management/update/historys',
        cache: false,
        type: 'post',
        data : JSON.stringify(requestObject),
        dataType: 'json',
        contentType: 'application/json',
        success: function (e) {
            // new History().clear();
            empty($(document.getElementsByClassName("historys_container")[0]));
            setHistory();
        }
    })
}


export function setIndexHistory(){
    console.log('ajax.js - getHistory index.........');

    let result = getLimitMoney();

    result.then(
        function(success){
            console.log(success)
            let current_date = getCurrentDate();
            $.ajax({
                url: '/money_management/index',
                cache: false,
                type: 'post',
                //아이디와 개인방인지 멀티방인지 구분할 수 있는 유니크 값을 같이 보내야 한다
                //만약 년/월/일만 보내면 개인, 멀티 구분없이 모든 데이터를 다 가져오는 버그 발생
                data: JSON.stringify({
                    year: current_date[0],
                    month: current_date[1],
                }),
                dataType: 'json',
                contentType: 'application/json',
                success: function (e) {
                    emptyActualDate();
                    insertHistorys(e);
                    insertHistoryToCalendar(e);
                    bindClickEventOnElement('.actualDate', function (e) {
                        console.log(roomType);
                        console.log("날짜 클릭")

                        let target_date = e.currentTarget.attributes.value.value;
                        let obj = dates_obj[target_date];
                        if (obj !== undefined) {
                            getSelectedDate(obj);
                        }
                    })
                }
            })
        },
        function(error){
            console.log(error);
            // activeModal()
        }
    )

}


export function setHistory(rid){
    console.log(rid);
    console.log('ajax.js - getHistory.........');

    let result = getLimitMoney(rid);

    result.then(
        function(success){
            console.log(success)
            let current_date = getCurrentDate();
            $.ajax({
                url: '/money_management/get/historys',
                cache: false,
                type: 'post',
                //아이디와 개인방인지 멀티방인지 구분할 수 있는 유니크 값을 같이 보내야 한다
                //만약 년/월/일만 보내면 개인, 멀티 구분없이 모든 데이터를 다 가져오는 버그 발생
                data: JSON.stringify({ 
                    year: current_date[0],
                    month: current_date[1],
                    date: current_date[2],
                    roomType: rType
                }),
                dataType: 'json',
                contentType: 'application/json',
                success: function (e) {
                    emptyActualDate();
                    insertHistorys(e);
                    insertHistoryToCalendar(e);
                    bindClickEventOnElement('.actualDate', function (e) {
                        console.log(roomType);
                        console.log("날짜 클릭")
                            
                        let target_date = e.currentTarget.attributes.value.value;
                        let obj = dates_obj[target_date];
                        if (obj !== undefined) {
                            getSelectedDate(obj);
                        }
                    })
                }
            })
        },
        function(error){
            console.log(error);
            // activeModal()
        }
    )

}
export function deleteHistorys(obj, deletedInputForm){
    $.ajax({
        url: '/money_management/delete/history',
        cache: false,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify({
            year: obj.year,
            month: obj.month,
            date: obj.date,
            content_no: obj.content_no
        }),
        success: function (e) {
            setHistory();
            updateHistoryContainer(deletedInputForm);
        }
    })
}
export function saveHistorys(history){ //roomType도 추가해야됨
    console.log("saveHistorys........")
    $.ajax({
        url: '/money_management/save',
        cache: false,
        data: JSON.stringify(history.get()),
        dataType: 'json',
        contentType: 'application/json',
        type: 'post',
        success: function () {
            history.clear();
            setHistory();
        }
    })
}
export function saveLimitMoney(money, rid){
    let cdate = getCurrentDate()
    if(money != null) {
        $.ajax({
            url: "/money_management/save/limit_money",
            cache: false,
            async: true,
            type: 'post',
            contentType: 'application/json',
            data: JSON.stringify({
                year: cdate[0],
                month: cdate[1],
                limit_money: money,
                rid: rid
            }),
            success: function (e) {
                closeModal();
                setHistory();
            },
            error: function (e) {
                console.log('error');
            }
        })
    }else{
        alert("제한 금액을 설정해주세요")
    }
}

export function findLimitMoney(success, error, rid){
    let current_date = getCurrentDate();

    let limitMoneyId = {
        year: current_date[0],
        month: current_date[1]
    };
    if( rid !== -1 ) limitMoneyId = {...limitMoneyId, rid}
    console.log(limitMoneyId);
    $.ajax({
        url: '/money_management/get/limit_money',
        cache: false,
        type: 'post',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(limitMoneyId),
        success: function (e) {
            if(e[0] !== null){
                success();
                setLimitMoney(e);
            }else{
                activeModal();
                error();
                emptyMoney();
                empty($('.input-groups'))
            }
        },
        error: function(e){
            error();
        }
    })
}




/**
 * not async
 */
export function getLimitMoney(rid = -1){ //roomType 추가해야됨
    let promise = new Promise(function(resolve, reject) {
        findLimitMoney(
        () => resolve("success"),
        () => reject("error"),
            rid
        );
    });
    return promise;
}

