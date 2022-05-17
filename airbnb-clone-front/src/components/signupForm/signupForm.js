import { useState } from "react"
import axios from "axios"
import "./signupForm.css"

const SignupForm = () => {

    const [state, setState] = useState({ role: "Tenant" })

    const onChange = (event) => {
        let changedState = { ...state }
        changedState[event.target.name] = event.target.value
        setState(changedState)
        console.log(state)
    }

    const onSubmit = (event) => {
        event.preventDefault()
        let bodyFormData = new FormData();
        bodyFormData.append('role', 'Tenant')
        console.log(bodyFormData)
        // bodyFormData.append(state)
        axios.post(`http://localhost:8081/api/v1/users`,
            state)
            .then(response => {
                console.log(response)
            })
            .catch(err => console.log(err));
        console.log(state)
    }

    return (
        <div >
            <div className="emptyspace"></div>
            <div className="container">
                <form onSubmit={onSubmit}>
                    <label> Full name</label>
                    <input type={'text'} name="name" onChange={onChange} />
                    <label> Username</label>
                    <input type={'text'} name="userName" onChange={onChange} />
                    <label> Password</label>
                    <input type={'password'} name="password" onChange={onChange} className="password" />
                    <label>Sign up As</label>
                    <select name="role" onChange={onChange}>
                        <option>Tenant</option>
                        <option>Landlord</option>
                    </select>
                    <button type='submit' className="btn btn-primary btn-lg btn-block">Sign up</button>
                </form>
            </div>
        </div>

    )
}

export default SignupForm


