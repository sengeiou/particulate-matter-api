function drawLineChart(label, categories, series, responseTime, country, width, height) {
    Highcharts.chart("container", {
        chart: {
            type: "line",
            width,
            height
        },
        title: {
            text: "Particulate matter data of country"
        },
        subtitle: {
            text: "Country: " + country + " - (Response time: " + responseTime + " ms)"
        },
        xAxis: {
            categories,
            title: {
                text: "Time"
            }
        },
        yAxis: {
            title: {
                text: "PM values"
            }
        },
        tooltip: {
            formatter: function() {
                return "<strong>"+this.x+": </strong>"+ this.y;
            }
        },
        series: [{
            name: label,
            data: series
        }]
    });
}

// -------------------------------------------------- Main code --------------------------------------------------------

// Get url parameter
var params = getAllUrlParams();
var urlSuffix = encodeQueryData(params);
var country = params.country;
var width = params.width ? params.width : 800;
var height = params.height ? params.height : 500;

// Execute request for data
$.ajax({
    url: "data/chart?" + urlSuffix,
    success: (result) => {
        var field = JSON.parse(result).field;
        var time = JSON.parse(result).time;
        var values = JSON.parse(result).values;
        var responseTime = JSON.parse(result).responseTime;
        drawLineChart(field, time, values, responseTime, country, width, height);
    }
});