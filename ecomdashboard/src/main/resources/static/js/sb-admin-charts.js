// Chart.js scripts
// -- Set new default font family and font color to mimic Bootstrap's default styling
Chart.defaults.global.defaultFontFamily = '-apple-system,system-ui,BlinkMacSystemFont,"Segoe UI",Roboto,"Helvetica Neue",Arial,sans-serif';
Chart.defaults.global.defaultFontColor = '#292b2c';

// -- Area Chart Example
var orLabels = $("#orLabels").val();
var orValues = $("#orValues").val();
orLabels = orLabels.split(",");
orLabels[0]=orLabels[0].substring(1);
orLabels[orLabels.length-1]=orLabels[orLabels.length-1].substring(0, orLabels[orLabels.length-1].length-1);
orValues = orValues.split(",");
orValues[0]=orValues[0].substring(1);
orValues[orValues.length-1]=orValues[orValues.length-1].substring(0, orValues[orValues.length-1].length-1);
var ctx = document.getElementById("myAreaChart");
var myLineChart = new Chart(ctx, {
  type: 'line',
  data: {
    labels: orLabels,
    datasets: [{
      label: "Orders",
      lineTension: 0.3,
      backgroundColor: "rgba(2,117,216,0.2)",
      borderColor: "rgba(2,117,216,1)",
      pointRadius: 5,
      pointBackgroundColor: "rgba(2,117,216,1)",
      pointBorderColor: "rgba(255,255,255,0.8)",
      pointHoverRadius: 5,
      pointHoverBackgroundColor: "rgba(2,117,216,1)",
      pointHitRadius: 20,
      pointBorderWidth: 2,
      data: orValues,
    }],
  },
  options: {
    scales: {
      xAxes: [{
        time: {
          unit: 'date'
        },
        gridLines: {
          display: false
        },
        ticks: {
          maxTicksLimit: 7
        }
      }],
      yAxes: [{
        ticks: {
          min: 100,
          max: 200,
          maxTicksLimit: 5
        },
        gridLines: {
          color: "rgba(0, 0, 0, .125)",
        }
      }],
    },
    legend: {
      display: false
    }
  }
});
// -- Bar Chart Example

var crLabels = $("#crLabels").val();

crLabels = crLabels.split(",");

var ctx = document.getElementById("myBarChart");
var myLineChart = new Chart(ctx, {
  type: 'bar',
  data: {
    labels: crLabels,
    datasets: [{
      label: "Revenue",
      backgroundColor: "rgba(2,117,216,1)",
      borderColor: "rgba(2,117,216,1)",
      data: [23000, 31000, 32000, 36000, 37000, 45000],
    }],
  },
  options: {
    scales: {
      xAxes: [{
        time: {
          unit: 'month'
        },
        gridLines: {
          display: false
        },
        ticks: {
          maxTicksLimit: 6
        }
      }],
      yAxes: [{
        ticks: {
          min: 0,
          max: 50000,
          maxTicksLimit: 5
        },
        gridLines: {
          display: true
        }
      }],
    },
    legend: {
      display: false
    }
  }
});
// -- Pie Chart Example
var ctx = document.getElementById("myPieChart");
var labelSet = $("#bsLabels").val();
var dataSet = $("#bsPercentages").val();
labelSet = labelSet.split(",");
labelSet[0]=labelSet[0].substring(1);
labelSet[labelSet.length-1]=labelSet[labelSet.length-1].substring(0, labelSet[labelSet.length-1].length-1);
dataSet = dataSet.split(",");
dataSet[0]=dataSet[0].substring(1);
dataSet[dataSet.length-1]=dataSet[dataSet.length-1].substring(0, dataSet.length-2);
var myPieChart = new Chart(ctx, {
  type: 'pie',
  data: {
    labels: labelSet,
    datasets: [{
      data: dataSet,
      backgroundColor: ['#007bff', '#dc3545', '#ffc107', '#28a745'],
    }],
  },
});
