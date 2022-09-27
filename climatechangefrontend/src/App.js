import './App.css';
import React, { Component } from 'react';
import SeasonWeather from "./weather.components/SeasonWeather";

class App extends Component {

  render() {
    return (
        <div className="App">
            <SeasonWeather/>
        </div>
    );
  }
}

export default App;
