import "./savePropertyForm.css";
import axios from "axios";
import { useState } from "react";


const SavePropertyForm = () => {

    const [state, setState] = useState({});

    const onInputchange = (event) => {
        let changedState = {...state}
        changedState[event.target.name] = event.target.value
        setState(changedState);
        console.log(state)
      }

    const onSubmit = (e) => {
        e.preventDefault();
        let propertyDTO = {...state}
        let address = {
            "state" : delete propertyDTO.state,
            "city": delete propertyDTO.city,
            "addressLine": delete propertyDTO.addressLine,
            "zipcode": delete propertyDTO.zipcode
        }
        propertyDTO["address"] = address;
        console.log(propertyDTO)
        axios.post('http://localhost:8081/api/v1/properties', propertyDTO)
        .then(response => console.log(response))
        .catch(err => console.log(err));
        //console.log(state)
    }

    return (
        <div className="container">
            <label className="col-sm-2"></label>
            <form className="col-sm-8" onSubmit={onSubmit}>
                <label>Number of rooms</label>
                <input type={'text'} name="numberOfRooms" onChange={onInputchange}/> 
                <label>Occupied</label>
                <select name="available" onChange={onInputchange}> 
                    <option>true</option>
                    <option>false</option>
                </select>
                <h4>Address</h4>
                <label>State</label>
                <input type={'text'} name='state' onChange={onInputchange}/>
                <label>City</label>
                <input type={'text'} name="city" onChange={onInputchange}/>
                <label>Address Line</label>
                <input type={'text'} name="addressLine" onChange={onInputchange}/>
                <label>Zipcode</label>
                <input type={'text'} name="zipcode" onChange={onInputchange}/>
                <label>Upload Pictures</label>
                <input type="file" id="images" name="images" multiple onChange={onInputchange}/>
                <button type='submit' className="btn btn-primary btn-lg btn-block">Save</button>
            </form>
        </div>
    )
}
export default SavePropertyForm;