///* globals Chart:false, feather:false */
//
//(function () {
////  'use strict'
////
////  feather.replace()
//
//    // Graphs
//    var ctx = document.getElementById('myChart').getContext('2d');
//    // eslint-disable-next-line no-unused-vars
//    var myChart = new Chart(ctx, {
//        type: 'pie',
//        data: {
//            labels: [
//                'Sunday',
//                'Monday',
//                'Tuesday',
//                'Wednesday',
//                'Thursday',
//                'Friday',
//                'Saturday'
//            ],
//            datasets: [{
//                    data: [
//                        document.getElementById('sunday').value,
//                        document.getElementById('monday').value,
//                        document.getElementById('tuesday').value,
//                        document.getElementById('wednesday').value,
//                        document.getElementById('thursday').value,
//                        document.getElementById('friday').value,
//                        document.getElementById('saturday').value
//                    ],
//                    lineTension: 0,
//                    backgroundColor: ['#e91e63', '#00e676', '#ff5722', '#1e88e5', '#ffd600', '#1e88e5', '#00e676'],
//                    borderColor: '#007bff',
//                    borderWidth: 4,
//                    pointBackgroundColor: '#007bff'
//                }]
//        },
//        options: {
//            title: {
//                display: true,
//                text: 'Recommended Daily Diet',
//                position: 'top',
//                fontSize: 16,
//                fontColor: '#111',
//                padding: 20
//            },
//            legend: {
//                display: true,
//                position: 'bottom',
//                labels: {
//                    boxWidth: 20,
//                    fontColor: '#111',
//                    padding: 15
//                }
//            },
//            tooltips: {
//                enabled: false
//            },
//            plugins: {
//                datalabels: {
//                    color: '#111',
//                    textAlign: 'center',
//                    font: {
//                        lineHeight: 1.6
//                    },
//                    formatter: function (value, ctx) {
//                        return ctx.chart.data.labels[ctx.dataIndex] + '\n' + value + '%';
//                    }
//                }
//            }
//        }
//    })
//})()
var ctx = document.getElementById('myChart').getContext('2d');
var myChart = new Chart(ctx, {
    type: 'pie',
    data: {
        labels: [
            'Sunday',
            'Monday',
            'Tuesday',
            'Wednesday',
            'Thursday',
            'Friday',
            'Saturday'
        ],
        datasets: [{
                data: [
                    document.getElementById('sunday').value,
                    document.getElementById('monday').value,
                    document.getElementById('tuesday').value,
                    document.getElementById('wednesday').value,
                    document.getElementById('thursday').value,
                    document.getElementById('friday').value,
                    document.getElementById('saturday').value
                ],
                lineTension: 0,
                backgroundColor: ['red', 'green', 'blue', 'orange', 'pink', 'brown', 'grey'],
                borderWidth: 0.5,
                borderColor: '#ddd'
            }]
    },
    options: {
        title: {
            display: true,
            text: 'Stats by day of the week',
            position: 'top',
            fontSize: 16,
            fontColor: '#111',
            padding: 20
        },
        legend: {
            display: true,
            position: 'bottom',
            labels: {
                boxWidth: 20,
                fontColor: '#111',
                padding: 15
            }
        },
        tooltips: {
            enabled: true
        },
        plugins: {
            datalabels: {
                color: '#111',
                textAlign: 'center',
                font: {
                    lineHeight: 1.6
                },
                formatter: function (value, ctx) {
                    return ctx.chart.data.labels[ctx.dataIndex] + '\n' + value + '%';
                }
            }
        }
    }
});