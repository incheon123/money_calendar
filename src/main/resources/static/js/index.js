/**
 * 저번 달/다음 달 버튼에 이벤트를 부여한다<br>
 * 하나는 달력 내용(history)를 비운다<br>
 * 또 다른 하나는 달력을 클릭하면 bindClickEventOnElementWhichIsActualDate 함수를 호출한다.<br>
 * @param ele
 */
function bindClickEventOnElementWhichIsPreOrNextBtn(ele){
    bindClickEventOnElement(ele, function(e){
        if(ele === '.pre-month') setDateToPreMonth();
        else setDateToNextMonth();

        getLimitMoney().then(
            function(success){
            },
            function(error){
                emptyMoney();
                empty($('.input-groups'))
                activeModal();
            }
        )

        empty($(".historys_container"));
        bindClickEventOnElementWhichIsActualDate("PRIVATE");
    })
}