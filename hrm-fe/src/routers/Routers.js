import { useEffect, useState } from "react";
import { Route } from "react-router-dom";
import { Constants } from "../constants/Constants";
import { Services } from "../services/Services";
import SignIn from "../components/SignIn";

const components = [

];

export default function() {
    const [token, setToken] = useState('');
    
    useEffect(() => {
        if (Object.keys(token).length !== 0 || (localStorage.getItem('token') && localStorage.getItem('token').length !== 0)) {
            Services.get(Constants.api.auth.me, (data) => alert('Hello ' + data.body.fullName), (data) => {
                throw new Error(data.body)
            });
        }
    }, [token]);
    if (Object.keys(token).length === 0 && (!localStorage.getItem('token') && localStorage.getItem('token').length === 0)) {
        return <SignIn setToken={(token) => setToken(token)} />
    }

    return (
        <span>routers</span>
    )
}