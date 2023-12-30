import React, { useState } from 'react';

function RegistrationForm() {
 const [username, setUsername] = useState("");
 const [password, setPassword] = useState("");
 const [confirmPassword, setConfirmPassword] = useState("");
 const [email, setEmail] = useState("");
 const [role, setRole] = useState("ROLE_USER"); // predefined role
 const [passwordError, setPasswordError] = useState("");

 const handleSubmit = async (event) => {
  event.preventDefault();

  if (password !== confirmPassword) {
    setPasswordError("Passwords do not match!");
    return;
  } else {
    setPasswordError("");
  }

  try {
    const response = await fetch('http://localhost:8080/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({
        username,
        password,
        email,
        role
      }),
    });

    if (!response.ok) {
      throw new Error('Network response was not ok');
    }

    const data = await response.json();
    alert(data);
  } catch (error) {
    console.error('Error:', error);
  }
 };

 return (
  <div>
    <h2>Registration Form</h2>
    <form onSubmit={handleSubmit}>
      <label>
        Username:
        <input type="text" value={username} onChange={e => setUsername(e.target.value)} />
      </label>
      <label>
        Password:
        <input type="password" value={password} onChange={e => setPassword(e.target.value)} />
      </label>
      <label>
        Confirm Password:
        <input type="password" value={confirmPassword} onChange={e => setConfirmPassword(e.target.value)} />
      </label>
      <label>
        Email:
        <input type="email" value={email} onChange={e => setEmail(e.target.value)} />
      </label>
      <input type="submit" value="Register" />
    </form>
    {passwordError && <p>{passwordError}</p>}
  </div>
 );
}

export default RegistrationForm;
