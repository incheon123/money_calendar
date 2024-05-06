/**
 * 인자 url 파싱 함수
 * @param {*} url 
 */
export function parseUrl(url = ""){

    if(url != ""            && 
       url !== null         && 
       url !== undefined    && 
       url.trim().length !== 0
    ){

        urlFragment = url.split('/')
        roomId = urlFragment[urlFragment.length - 1]

        return roomId;
    }

    return -1;
}