
//방 리스트
const roomList = document.getElementsByClassName("roomList")[0];
//비밀번호 체크박스
const roomPwCheckBox = document.getElementById("roomPwCheckBox");
//비밀번호
const pw = document.getElementById("room_pw");
//방 제목
const roomName = document.getElementsByClassName("roomName")[0];
//방 인원
const memberNum = document.getElementById("memberNum");
//
const pw_group = document.getElementById("password-group");

const btnCloseModal = document.getElementsByClassName('close-modal')[0];
const form = document.getElementsByClassName('modal-content')[0];
function openModal() {
    $(document).ready(function () {
        $('.modal').modal('show');
    })
}
function closeModal(){
    $(document).ready(function () {
        $('.modal').modal('hide');
    })
}

/**
 * .modal-submit 클래스에 click event 부여
 */
$('.modal-submit').click(function(){

    if(roomName.value == null || roomName.value == '') {
        return false;
    }
    if(!(roomName.value.length >= 3 && roomName.value.length <= 20)) {
        return false;
    }
    if(!(/^[0-9]+$/.test(memberNum.value))) {
        return false;
    }
    if(!Number(memberNum.value) >= 1 && Number(memberNum.value) <= 10) {
        return false;
    }
    if(!roomPwCheckBox.checked){
        if(pw.value == null || pw.value == ''){
            return false;
        }
    }
    if(!pw.value.length >= 8 && pw.value.length <= 20) {
        return false;
    }

})

function main(){
    $('.create-room').click( () => openModal())
    $(btnCloseModal).click( () => closeModal() );

    $(roomPwCheckBox).click( () => {
        if(roomPwCheckBox.checked){
            console.log("clicked")
            $(pw).attr('disabled', 'disabled')
            $(pw).val(' ');
        }else{
            $(pw).removeAttr('disabled')
        }
    } );

}


main();