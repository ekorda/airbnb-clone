import "./totalIncomeForLocation.css"
import axios from "axios";
import { useState } from "react";

const TotalIncomeForLocation = () => {
    const [state, setState] = useState({})

    const onInputChange = (event) => {
        let changedState = {...state}
        changedState[event.target.name] = event.target.value
        setState(changedState)
        console.log(state)
    }
    
    const onSubmit = (event) => {
        event.preventDefault()
        console.log(state)
        axios.get(`http://localhost:8081/api/v1/properties/total-income-for/${state.city}`, 
        state)
        .then(response => {
            let changedState = {...state}
            changedState["totalIncome"] = response.data
            setState(changedState)
            //console.log(response)
        })
        .catch(err => console.log(err));
    }

    return (
       <div>
           <form onSubmit={onSubmit}>
               <input type={'text'} name="city" onChange={onInputChange}/>
               <label>Total Income   {state.totalIncome}</label>
               <button type='submit' className="btn btn-primary btn-lg btn-block">Total Income</button>             
           </form>
       </div>
    )
}

export default TotalIncomeForLocation;