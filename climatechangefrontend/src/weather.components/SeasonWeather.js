import React, { Component } from 'react';

export class SeasonWeather extends Component {

    state = {
        seasonWeatherInf: []
    };

    async componentDidMount() {
        const response = await fetch('/seasonweather');
        const body = await response.json();
        this.setState({seasonWeatherInf: body.data});
    }

    getSeasonWeatherInfo(seasonWeatherInf) {
        let dict = {};
        seasonWeatherInf
            .sort((firstWeatherInfo, secondWeatherInfo) =>
                firstWeatherInfo.year > secondWeatherInfo.year ? 1 : -1)
            .forEach(tempSeasonInfo => {
                if(dict[tempSeasonInfo.year] == null) {
                    dict[tempSeasonInfo.year] = {}
                    dict[tempSeasonInfo.year][tempSeasonInfo.season] = {}
                    dict[tempSeasonInfo.year][tempSeasonInfo.season]['minTemp'] = tempSeasonInfo.minTemp;
                    dict[tempSeasonInfo.year][tempSeasonInfo.season]['maxTemp'] = tempSeasonInfo.maxTemp;
                } else {
                    dict[tempSeasonInfo.year][tempSeasonInfo.season] = {}
                    dict[tempSeasonInfo.year][tempSeasonInfo.season]['minTemp'] = tempSeasonInfo.minTemp;
                    dict[tempSeasonInfo.year][tempSeasonInfo.season]['maxTemp'] = tempSeasonInfo.maxTemp;
                }
            })
        console.log(dict);
        return dict;
    }

    render() {
        const {seasonWeatherInf} = this.state;
        const dictSeasonWeatherInf = this.getSeasonWeatherInfo(seasonWeatherInf);
        return (
            <div>
                <header className="App-header">
                    <div className="App-intro">
                        <table border={1}>
                            <caption>Weather</caption>

                            <tr>
                                <th rowSpan={2}>Year</th>
                                <th colSpan={2}>Winter</th>
                                <th colSpan={2}>Spring</th>
                                <th colSpan={2}>Summer</th>
                                <th colSpan={2}>Autumn</th>
                            </tr>
                            <tr>
                                <th>min t</th>
                                <th>max t</th>
                                <th>min t</th>
                                <th>max t</th>
                                <th>min t</th>
                                <th>max t</th>
                                <th>min t</th>
                                <th>max t</th>
                            </tr>
                        {
                            Object.keys(dictSeasonWeatherInf).map((year) =>
                                <tr>
                                    <td>{year}</td>
                                    <td>{dictSeasonWeatherInf[year]['Winter']['minTemp']}</td>
                                    <td>{dictSeasonWeatherInf[year]['Winter']['maxTemp']}</td>
                                    <td>{dictSeasonWeatherInf[year]['Spring'] == null ? "" : dictSeasonWeatherInf[year]['Spring']['minTemp']}</td>
                                    <td>{dictSeasonWeatherInf[year]['Spring'] == null ? "" : dictSeasonWeatherInf[year]['Spring']['maxTemp']}</td>
                                    <td>{dictSeasonWeatherInf[year]['Summer'] == null ? "" : dictSeasonWeatherInf[year]['Summer']['minTemp']}</td>
                                    <td>{dictSeasonWeatherInf[year]['Summer'] == null ? "" : dictSeasonWeatherInf[year]['Summer']['maxTemp']}</td>
                                    <td>{dictSeasonWeatherInf[year]['Autumn'] == null ? "" : dictSeasonWeatherInf[year]['Autumn']['minTemp']}</td>
                                    <td>{dictSeasonWeatherInf[year]['Autumn'] == null ? "" : dictSeasonWeatherInf[year]['Autumn']['maxTemp']}</td>
                                </tr>
                        )}
                        </table>
                    </div>
                </header>
            </div>
        );
    }
}

export default SeasonWeather;