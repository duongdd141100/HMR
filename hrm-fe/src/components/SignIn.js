import { useState } from "react";
import { Constants } from "../constants/Constants";
import { Services } from "../services/Services";
import '../css/Common.css';

const divStyle = {
    // display: 'flex',
    // justifyContent: 'center',
    // alignItems: 'center',
    // height: '100%',
    // flexDirection: 'column',
    display: 'grid',
    'grid-template-rows': 'repeat(12, 1fr)'
}

const titleStyle = {
    'grid-row': '2',
    'text-align': 'center'
}

const buttonStyle = {
    'grid-row': '4',
    'text-align': 'center'
}

const emailPassStyle = {
    display: 'grid',
    'grid-template-columns': 'repeat(12, 1fr)'
}

export default function({ setToken }) {
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    return (
        <div style={divStyle}>
            <div style={titleStyle}>
                <h2>HRM</h2>
            </div>
            <div style={{'grid-row': '3'}}>
                <div style={emailPassStyle}>
                    <label style={{'grid-column': '6'}}>Email: </label>
                    <input style={{'grid-column': '7'}} type='text' onChange={e => setEmail(e.target.value)}/>
                </div>
                <div style={emailPassStyle}>
                    <label style={{'grid-column': '6'}}>Password: </label>
                    <input style={{'grid-column': '7'}} type='password' onChange={e => setPass(e.target.value)}/>
                </div>
            </div>
            <div style={buttonStyle}>
                <button className="successButton" onClick={() => Services.post(Constants.api.auth.signIn,
                    JSON.stringify({
                        email: email,
                        password: pass
                    }),
                    data => {
                        setToken(`Bearer ${data.body}`);
                        localStorage.setItem('token', `Bearer ${data.body}`);
                    },
                    data => { throw new Error(data.body) })}>Sign In</button>
            </div>
        </div>
    );
}