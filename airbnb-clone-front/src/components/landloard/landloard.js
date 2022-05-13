import React, { useState } from "react";
import "./landloard.css";
import UserService from "../../services/UserService";

function Landloard() {
  const Property = {};
  const [PropertyState, setPropertyState] = useState({
    location: "",
    RoomNbr: "",
    isOccupied: ""
  });
  const [propertiesState, setPropertiesState] = useState([
    { location: "ddddd", RoomNbr: "5", isOccupied: "yes" }
  ]);
  const onFieldsChanged = event => {
    let copy = { ...PropertyState };
    copy[event.target.name] = event.target.value;
    setPropertyState(copy);
  };

  const onAddProperty = () => {
    PropertyState["isOccupied"] = "no";
    let copy = [...propertiesState];
    copy.push({ ...PropertyState });
    setPropertiesState(copy);
    setPropertyState({
      location: "",
      RoomNbr: "",
      isOccupied: ""
    });
  };

  return (
    <div>
      <h1> LandLord : {UserService.getUsername()} </h1>

      <table id="customers">
        <tr>
          <th>location</th>
          <th>number of rooms</th>
          <th>isOccupied</th>
        </tr>

        {propertiesState.map(item => (
          <tr>
            <td>{item.location}</td>
            <td>{item.RoomNbr}</td>
            <td>{item.isOccupied}</td>
          </tr>
        ))}
      </table>

      <h1> Add Property : </h1>

      <label>Location</label>
      <input
        type="text"
        name="location"
        placeholder="location.."
        value={PropertyState.location}
        onChange={onFieldsChanged}
      />

      <label>Number of room</label>
      <input
        type="text"
        name="RoomNbr"
        value={PropertyState.RoomNbr}
        placeholder="Your last name.."
        onChange={onFieldsChanged}
      />

      <input type="submit" value="Submit" onClick={onAddProperty} />
    </div>
  );
}

export default Landloard;
