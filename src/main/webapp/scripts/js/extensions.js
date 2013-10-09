function serializeObject(formData) {
    var data = {};

    $(document.forms["addItem"]).serializeArray().forEach(
        function (e) { data[e.name] = e.value; }
    );

    return data;
}
