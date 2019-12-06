import React from "react";
import { Form } from "../component";
import { stateStore, setHeaderTitle } from "../statestore/AppState";

export default class AddPost extends React.Component {
    componentWillMount = () => stateStore.dispatch(setHeaderTitle("Add Post"))
    
    render() {
        return (
            <Form/>
        )
    }
}
