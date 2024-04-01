
export function setLineChartData(labels, income, outcome){
    const data = {
        labels: labels,
        datasets: [
            {
                label: '수입',
                data: income,
                yAxisID: 'y',
            },
            {
                label: '지출',
                data: outcome,
                yAxisID: 'y1',
            }
        ]
    };

    return data;
}

export function setLineChartConfig(data){
    const config = {
        type: 'line',
        data: data,
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
                    text: 'year income'
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