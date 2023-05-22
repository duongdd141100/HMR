import { useEffect, useState } from "react";
import { Route, Routes } from "react-router-dom";
import { Constants } from "../constants/Constants";
import { Services } from "../services/Services";
import SignIn from "../components/SignIn";
import SignOut from "../components/SignOut"
import Home from "../components/Home";
import MainLayout from "../layouts/MainLayout";

const components = {
    "/home": <Home />,
    "/logout": <SignOut />
}

export default function Routers() {
    const [token, setToken] = useState('');
    const [header, setHeader] = useState([]);

    useEffect(() => {
        if (Object.keys(token).length !== 0 || (localStorage.getItem('token') && localStorage.getItem('token').length !== 0)) {
            Services.get(Constants.api.auth.me, (data) => console.log('Hello ' + data.body.fullName), (data) => {
                throw new Error(data.body)
            });
            Services.get(Constants.api.entries.headers, (data) => setHeader(data.body), (data) => {
                throw new Error(data.body)
            })
        }
    }, [token]);

    if (Object.keys(token).length === 0 && (!localStorage.getItem('token') && localStorage.getItem('token')?.length === 0)) {
        return <SignIn setToken={(token) => setToken(token)} />
    }

    return (
        <MainLayout header={header}>
        <Routes>
            {header.map(item => { 
                return (
                <Route
                    key={item.slug}
                    path={item.slug}
                    element={(
                        
                            components[item.slug]
                        
                    )}
                />
            )})}
        </Routes>
        </MainLayout>


    )
}