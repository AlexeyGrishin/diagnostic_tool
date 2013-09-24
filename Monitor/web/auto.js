var Updator = {
    interval: undefined,

    turnOn: function() {
        this.interval = setInterval(Updator.update, 10000);
    },

    turnOff: function() {
        clearInterval(this.interval);
    },

    update: function() {
        var ctainer = $("#table");
        var urlSep = document.location.href.indexOf("?") > -1 ? "&" : "?";
        $.get(document.location.href + urlSep + "js=1", function(data) {
            ctainer.html(data);
            Updator.onUpdate();
        });

    },

    onUpdate: function() {}
};

$(function() {

    $(" button.force").click(function() {
        //Updator.turnOff();
        var href = $(this).attr("href");
        $.get(href, function(d) {
            Updator.update();
        });
        return false;
    });

    Updator.onUpdate = function() {
        $("a.to-delete").click(function() {
            var href = $(this).attr("href");
            var $row = $(this).parents("tr");
            $row.hide();
            $.get(href, function(d) {
                Updator.update();
            });
            return false;
        });
    };

    Updator.turnOn();
    Updator.onUpdate();
});