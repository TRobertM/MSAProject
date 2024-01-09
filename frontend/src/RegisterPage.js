import React, { useState } from 'react';
import './css/RegisterPageCss.css';

const RegisterPage = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [password2, setPassword2] = useState('');
  const [message, setMessage] = useState('');
  const [email, setEmail] = useState('');
  const [usernameError, setUsernameError] = useState(false);
  const [emailError, setEmailError] = useState(false);
  const [passwordError, setPasswordError] = useState(false);

  const handleSubmit = async (event) => {
      event.preventDefault();
      if(password !== password2){
        setMessage("Passwords must match");
        setPasswordError(true);
        setUsernameError(false);
        setEmailError(false);
        return;
      }

      const response = await fetch('http://localhost:8080/register', {
          method: 'POST',
          headers: {
              'Content-Type': 'application/json',
          },
          body: JSON.stringify({
              username,
              password,
              email,
              role: 'ROLE_USER',
          }),
      });

      const data = await response.text();
      console.log(data);

      if (data === 'Username already exists') {
        setMessage(data);
        setUsernameError(true);
        return;
      } else {
        setUsernameError(false);
      }

      if (data === 'Email already exists') {
        setMessage(data);
        setEmailError(true);
        return;
      } else {
        setEmailError(false);
      }

      setUsernameError(false);
      setEmailError(false);
      setPasswordError(false);
      setMessage(data);
  };

  return (
    <div className='registration'>
    <div className='registration-form'>
      <p className='backButton'><a className='backButtonLink' href="/Login">&lt;</a></p>
      <form onSubmit={handleSubmit} className='register-form-input'>
          <label>
            <input placeholder = "Username" type="text" value={username} onChange={e => setUsername(e.target.value)} className={usernameError ? 'error' : ''} required/>
          </label>
          <label>
            <input placeholder = "Password" type="password" value={password} onChange={e => setPassword(e.target.value)} className={passwordError ? 'error' : ''} required/>
          </label>
          <label>
            <input placeholder = "Password again" type="password" value={password2} onChange={e => setPassword2(e.target.value)} className={passwordError ? 'error' : ''} required/>
          </label>
          <label>
            <input placeholder = "Email" type="email" value={email} onChange={e => setEmail(e.target.value)} className={emailError ? 'error' : ''} required/>
          </label>
          <label>
            <button type="submit" className='submit-register'>Register</button>
          </label>
          <p className={usernameError || emailError || passwordError ? 'error' : 'success'}>{message}</p>
      </form>
    </div>
    </div>
  );
};

export default RegisterPage;
