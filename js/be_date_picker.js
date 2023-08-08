$('div[id^="be-date-picker-"]').each(function (index) {
  var thisID = $(this).attr('id');

  var language = $("#" + thisID).attr("data-lang");
  var dateFormat = $("#" + thisID).attr("data-dateFormat");
  var datepickerColor = $("#" + thisID).attr("data-datepickerColor");
  var calendarWidth = $("#" + thisID).attr("data-calendarWidth");
  var themeStyle = $("#" + thisID).attr("data-style");
  var infoBox = $("#" + thisID).attr("data-infoBox");
  var inputID = $("#" + thisID).attr("data-inputID");
  var weekStart = $("#" + thisID).attr("data-weekStart");
  var fontFamily = $("#" + thisID).attr("data-fontFamily");
  infoBox = infoBox.toString();


  $('head').append('<style type="text/css">.mp-picker{width:' + calendarWidth + '!important}</style>');
  if (fontFamily) {
    $('head').append('<style type="text/css">.mp-picker{font-family:' + fontFamily + '}</style>');
  }


  if (infoBox === '0') {
    $('head').append('<style type="text/css">.mp-picker .mp-picker-info{display:none!important}</style>');
    $('head').append('<style type="text/css">.mp-picker .mp-picker-picker{width:100%!important}</style>');
  }



  var inputIDString = "#" + inputID;

  var monthpicker = new MaterialDatepicker(inputIDString, {
    lang: language,
    outputFormat: dateFormat,
    color: datepickerColor,
    weekBegin: weekStart,
    theme: themeStyle
  });

});