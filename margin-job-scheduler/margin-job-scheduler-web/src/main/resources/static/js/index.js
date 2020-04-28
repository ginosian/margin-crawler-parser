$(function () {

    //run job
    $(".btnRun").click(function () {
        var jobId = $(this).parent().attr("id");
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: "/api/run",
            dataType: "json",
            data: JSON.stringify({
                "channel": $("#channel_" + jobId).text(),
                "country": $("#country_" + jobId).text()
            }),
            success: function (res) {
                if (res.valid) {
                    alert("run success!");
                } else {
                    alert(res.msg);
                }
            }
        });
    });

    //pause job
    $(".btnPause").click(function () {
        var jobId = $(this).parent().attr("id");
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: "/api/pause",
            dataType: "json",
            data: JSON.stringify({
                "channel": $("#channel_" + jobId).text(),
                "country": $("#country_" + jobId).text()
            }),
            success: function (res) {
                if (res.valid) {
                    alert("pause success!");
                    location.reload();
                } else {
                    alert(res.msg);
                }
            },
        });
    });

    //resume job
    $(".btnResume").click(function () {
        var jobId = $(this).parent().attr("id");
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: "/api/resume",
            dataType: "json",
            data: JSON.stringify({
                "channel": $("#channel_" + jobId).text(),
                "country": $("#country_" + jobId).text()
            }),
            success: function (res) {
                if (res.valid) {
                    alert("resume success!");
                    location.reload();
                } else {
                    alert(res.msg);
                }
            }
        });
    });

    //delete job
    $(".btnDelete").click(function () {
        var jobId = $(this).parent().attr("id");
        $.ajax({
            type: 'POST',
            contentType: 'application/json',
            url: "/api/delete",
            dataType: "json",
            data: JSON.stringify({
                "channel": $("#channel_" + jobId).text(),
                "country": $("#country_" + jobId).text()
            }),
            success: function (res) {
                if (res.valid) {
                    alert("delete success!");
                    location.reload();
                } else {
                    alert(res.msg);
                }
            }
        });
    });

    // update cron expression
    $(".btnEdit").click(
        function () {
            $("#myModalLabel").html("cron edit");
            var jobId = $(this).parent().attr("id");
            $("#jobId").val(jobId).text();
            $("#edit_channel").val($("#channel_" + jobId).text());
            $("#edit_country").val($("#country_" + jobId).text());
            $("#edit_cron").val($("#cron_" + jobId).text());
            $("#edit_state").val($("#state_" + jobId).text());
            $("#edit_description").val($("#description_" + jobId).text());

            $('#edit_channel').attr("readonly", "readonly");
            $('#edit_country').attr("readonly", "readonly");
            $('#edit_description').attr("readonly", "readonly");

            $("#myModal").modal("show");
        });

    $("#save").click(
        function () {
            $.ajax({
                type: 'POST',
                contentType: 'application/json',
                url: "/api/save",
                data:  JSON.stringify(getFormData($('#mainForm'))),
                success: function (res) {
                    if (res.valid) {
                        alert("success!");
                        location.reload();
                    } else {
                        alert(res.msg);
                    }
                }
            });
        });

    function getFormData($form){
        var unindexed_array = $form.serializeArray();
        var indexed_array = {};

        $.map(unindexed_array, function(n, i){
            indexed_array[n['name']] = n['value'];
        });

        return indexed_array;
    }


    // create job
    $("#createBtn").click(
        function () {
            $("#myModalLabel").html("create job");

            $("#jobId").val("");
            $("#edit_channel").val("");
            $("#edit_country").val("");
            $("#edit_cron").val("");
            $("#edit_state").val("NORMAL");
            $("#edit_description").val("");

            // $('#edit_channel').removeAttr("readonly");
            // $('#edit_country').removeAttr("readonly");
            // $('#edit_description').removeAttr("readonly");

            $("#myModal").modal("show");
        });


});