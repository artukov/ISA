function formatDate(date, time){
    const [year, month, day] = date.split('-');
    if(time !== undefined || time !== null){
        const [hour, minute] = time.split(':');
        return day + "-" + month + "-" + year + " " + "00:" + minute + ":" + hour + " +0100";
    }
    //pattern dd-MM-yyyy ss:mm:HH Z
    return day + "-" + month + "-" + year + " " + "00:00:00 +0100";

}

export default formatDate;