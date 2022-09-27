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

    render() {
        const {seasonWeatherInf} = this.state;
        return (
            <div>
                <header className="App-header">
                    <div className="App-intro">
                        <h2>Weather</h2>
                        {seasonWeatherInf
                            .sort((a,b) => a.year > b.year ? 1 : -1)
                            .map(tempSeasonInfo =>
                            <div>
                                {tempSeasonInfo.season}
                                ({tempSeasonInfo.year})
                                ({tempSeasonInfo.minTemp})
                                ({tempSeasonInfo.maxTemp})
                            </div>
                        )}
                    </div>
                </header>
            </div>
        );
    }
}

export default SeasonWeather;