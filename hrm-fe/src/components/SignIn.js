import { useState } from "react";
import { Constants } from "../constants/Constants";
import { Services } from "../services/Services";

export default function({ setToken }) {
    const [email, setEmail] = useState('');
    const [pass, setPass] = useState('');
    return (
        <div>
            <div>
                <h3>HRM</h3>
            </div>
            <div>
                <div>
                    <label>Email: </label>
                    <input type='text' onChange={e => setEmail(e.target.value)}/>
                </div>
                <div>
                    <label>Password: </label>
                    <input type='password' onChange={e => setPass(e.target.value)}/>
                </div>
            </div>
            <div>
                <button onClick={() => Services.post(Constants.api.auth.signIn,
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