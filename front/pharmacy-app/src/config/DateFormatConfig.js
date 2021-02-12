function formatDate(date, time){
    const [year, month, day] = date.split('-');
    if(time !== undefined || time !== null){
        const [hour, minute,seconds] = time.split(':');
        if(seconds === null || seconds === undefined)
            return day + "-" + month + "-" + year + " " + time +":00 +0100";
        return day + "-" + month + "-" + year + " " + hour + ":" + minute + ":" + seconds + " +0100";
    }
    //pattern dd-MM-yyyy HH:mm:ss Z
    return day + "-" + month + "-" + year + " " + "00:00:00 +0100";

}

export default formatDate;