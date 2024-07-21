function registerQuery() {
    const startStation = $("#select-start :selected").val();
    const endStation = $("#select-end :selected").val();
    if (startStation === endStation) {
        alert("出發站不可等於到達站");
        return;
    }

    const queryDate = $("#query-date");
    const queryTime = $("#query-time");
    if (!queryDate.val()) {
        queryDate.val(getDate());
    }
    if (!queryTime.val()) {
        queryTime.val(getTime());
    }

    $("#form").submit();
}

function getDate() {
    return moment().format('YYYY-MM-DD');
}
function getTime() {
    return new Date().toTimeString().substring(0, 5);
}