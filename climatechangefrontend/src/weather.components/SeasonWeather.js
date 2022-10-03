import React, { Component } from 'react';

export class SeasonWeather extends Component {

    state = {
        seasonWeatherInf: [],
        seasons: ["Winter", "Spring", "Summer", "Autumn"],
        tempNames: ["minTemp", "maxTemp"]
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
                }
                dict[tempSeasonInfo.year][tempSeasonInfo.season] = {}
                dict[tempSeasonInfo.year][tempSeasonInfo.season]['minTemp'] = tempSeasonInfo.minTemp;
                dict[tempSeasonInfo.year][tempSeasonInfo.season]['maxTemp'] = tempSeasonInfo.maxTemp;
            })
        console.log(dict);
        return dict;
    }

    getValueForDisplay(year, seasonName, tempName, dictSeasonWeatherInf) {
        return dictSeasonWeatherInf[year][seasonName] == null ? "" : dictSeasonWeatherInf[year][seasonName][tempName];
    }

    render() {
        const {seasonWeatherInf, seasons, tempNames} = this.state;
        const dictSeasonWeatherInf = this.getSeasonWeatherInfo(seasonWeatherInf);
        return (
            <div>
                <header className="App-header">
                    <div className="App-intro">
                        <table border={1}>
                            <caption>Weather</caption>
                            <tr>
                                <th rowSpan={2}>Year</th>
                                {seasons.map(season => <th colSpan={2}>{season}</th>)}
                            </tr>
                            <tr>
                                {seasons.map(()=> tempNames.map((tempName) => <th>{tempName}</th>))}
                            </tr>
                            {
                                Object.keys(dictSeasonWeatherInf).map((year) =>
                                    <tr>
                                    <td>{year}</td>
                                        {
                                            seasons
                                                .map(
                                                    (season) => tempNames.map((tempName) =>
                                                        <td>
                                                            {this.getValueForDisplay(
                                                                year,
                                                                season,
                                                                tempName,
                                                                dictSeasonWeatherInf
                                                            )}
                                                        </td>
                                                    )
                                                )
                                        }
                                    </tr>
                                )
                            }
                        </table>
                    </div>
                </header>
            </div>
        );
    }
}

export default SeasonWeather;