/**
 * 인자 url 파싱 함수
 * @param {*} url 
 */
export function getRid(url = ""){

    if(url != ""            && 
       url !== null         && 
       url !== undefined    && 
       url.trim().length !== 0
    ){

        let urlFragment = url.split('/')
        let roomId = urlFragment[urlFragment.length - 1]

        return roomId;
    }

    return undefined;
}

export function getCurrentRoomUrl(){
    return window.location.href;
}