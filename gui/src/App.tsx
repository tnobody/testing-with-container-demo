import React from 'react';
import {Board} from "./Board";

const App: React.FC = () => {

    return (
        <div className="app">
            <main className="col">
                <h1>TicTacToe</h1>
                <Board/>
            </main>
        </div>
    );
}

export default App;
