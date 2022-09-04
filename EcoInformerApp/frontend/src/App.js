import logo from './logo.svg';
import './App.css';
import React, { Component } from 'react';

class App extends Component {
  state = {
    weatherinf: []
  };

  async componentDidMount() {
    const response = await fetch('/dailyweather');
    const body = await response.json();
    this.setState({weatherinf: body.data});
  }


  render() {
    const {weatherinf} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="App-intro">
              <h2>Weather</h2>
              {weatherinf.map(tempDailyInfo =>
                  <div>
                    {tempDailyInfo.date} ({tempDailyInfo.tavg}) ({tempDailyInfo.tmin}) ({tempDailyInfo.tmax})
                  </div>
              )}
            </div>
          </header>
        </div>
    );
  }
}

export default App;
