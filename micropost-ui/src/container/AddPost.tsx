import React from "react";
import { PostForm } from "../component";
import { stateStore, setHeaderTitle } from "../statestore/AppState";
import { Navigation } from "../common/Navigation";
import { RouteComponentProps } from "react-router";

export default class AddPost extends React.Component<RouteComponentProps> {
    componentWillMount = () => stateStore.dispatch(setHeaderTitle("Add Post"))

    render() {
        return <PostForm {...this.props}
            handleCancel={e => Navigation.navTo("/posts", this.props)} 
            endpointBaseUrl="/posts"
        />
    }

}
