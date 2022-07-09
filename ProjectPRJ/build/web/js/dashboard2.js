//var arr = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
var txt ="January;February;March;April;May;June;July;August;September;October;November;December";
var arr = txt.split(";");
var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: arr,
        datasets: [{
                label: 'sale',
                data: [document.getElementById('t1').value,
                    document.getElementById('t2').value,
                    document.getElementById('t3').value,
                    document.getElementById('t4').value,
                    document.getElementById('t5').value,
                    document.getElementById('t6').value,
                    document.getElementById('t7').value,
                    document.getElementById('t8').value,
                    document.getElementById('t9').value,
                    document.getElementById('t10').value,
                    document.getElementById('t11').value,
                    document.getElementById('t12').value],
                backgroundColor: [
                    'rgba(216, 27, 96, 0.6)',
                    'rgba(3, 169, 244, 0.6)',
                    'rgba(255, 152, 0, 0.6)',
                    'rgba(29, 233, 182, 0.6)',
                    'rgba(156, 39, 176, 0.6)',
                    'rgba(84, 110, 122, 0.6)',
                    'red',
                    'green',
                    'brown',
                    'yellow',
                    'lightblue',
                    'lightgreen'
                ],
                borderColor: [
                    'rgba(216, 27, 96, 1)',
                    'rgba(3, 169, 244, 1)',
                    'rgba(255, 152, 0, 1)',
                    'rgba(29, 233, 182, 1)',
                    'rgba(156, 39, 176, 1)',
                    'rgba(84, 110, 122, 1)',
                    'red',
                    'green',
                    'brown',
                    'yellow',
                    'lightblue',
                    'lightgreen'
                ],
                borderWidth: 1
            }]
    },
    options: {
        legend: {
            display: false
        },
        title: {
            display: true,
            text: 'Stats by month',
            position: 'top',
            fontSize: 16,
            padding: 20
        },
        scales: {
            yAxes: [{
                    ticks: {
                        min: 75
                    }
                }]
        }
    }
});