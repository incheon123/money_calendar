export function setBarChartData(labels, income, outcome) {
    let data = {
        labels: labels,
        datasets: [
            {
                label: '수입',
                data: [income],
                yAxisID: 'y',
            },
            {
                label: '지출',
                data: [outcome],
                yAxisID: 'y1',
            }
        ]
    };

    return data;
}

export function setBarChartConfig(d, type) {
    let config = {
        type: type,
        data: d,
        options: {
            responsive: true,
            interaction: {
                mode: 'index',
                intersect: false,
            },
            stacked: false,
            plugins: {
                title: {
                    display: true,
                    text: '월 수입/지출 합계'
                }
            },
            scales: {
                y: {
                    type: 'linear',
                    display: true,
                    position: 'left',
                },
                y1: {
                    type: 'linear',
                    display: true,
                    position: 'right',

                    // grid line settings
                    grid: {
                        drawOnChartArea: false, // only want the grid lines for one axis to show up
                    },
                },
            }
        },
    };

    return config;
}