import React from "react";
import { PagedList } from "../component";
import { setHeaderTitle, stateStore } from "../statestore/AppState";

export default class Posts extends React.Component {
    componentWillMount = () => stateStore.dispatch(setHeaderTitle("Posts"))

    render() {
        return (
            <PagedList 
                items={[{title:"item1"}, {title:"item2"}, {title:"item3"}]}
                pages={3} 
              />
        )
    }
}
