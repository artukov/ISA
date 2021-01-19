function formatDate(date){
    const [year, month, day] = date.split('-')
    //pattern dd-MM-yyyy ss:mm:HH Z
    return day + "-" + month + "-" + year + " " + "00:00:00 +0100";

}

export default formatDate;