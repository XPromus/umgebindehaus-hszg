import React from 'react';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {ListPage} from "./pages/ListPage";
import {EditPage} from "./pages/EditPage";
import {BrowserRouter as Router, Route, Routes, useParams} from "react-router-dom";


function App() {
    const params = useParams();
    console.log(params);
    return (
        <Router>
            <Routes>
                <Route path="/edit/:id" element={<EditPage/>}/>
                <Route path="/" element={<ListPage/>}/>
            </Routes>
        </Router>
    );
}

export default App;