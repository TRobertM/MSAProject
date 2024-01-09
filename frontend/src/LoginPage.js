import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import './css/LoginPageCss.css';

const LoginPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [message, setMessage] = useState('');
  const [loginError, setLoginError] = useState(false);
  const navigate = useNavigate();

  const handleLogin = async (event) => {
    event.preventDefault();
   
    const response = await fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            username,
            password,
        }),
    });
   
    const data = await response.text();
   
    if (data === 'Incorrect username or password' || data === 'Invalid user request!') {
        setMessage("Incorrect username or password");
        setLoginError(true);
        return;
    } else {
        localStorage.setItem('jwtToken', data);
        setLoginError(false);
        navigate('/Sneakers')
    }
   };

  return (
    <div className='authentication'>
    <div className='authentication-form'>
        <form onSubmit={handleLogin}>
            <label>
                <input placeholder = "Username" type="text" value={username} onChange={e => setUsername(e.target.value)} required/>
            </label>
            <label>
                <input placeholder = "Password" type="password" value={password} onChange={e => setPassword(e.target.value)} required/>
            </label>
            <label>
                <button type="submit" className='login-button'>Login</button>
            </label>
            <p className='error'>{message}</p>
            <p className='registration-redirect'><a href="/Register">Don't have an account? Register now.</a></p>
        </form>
    </div>
    </div>
  );
};

export default LoginPage;
