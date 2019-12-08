import React from "react";
import { PagedList } from "../component";
import { setHeaderTitle, stateStore } from "../statestore/AppState";
import { PagedListProps, Item } from "../component/PagedList";
import serviceBaseUrl from "../common/Constants";
import { RouteComponentProps } from "react-router";

export default class Posts extends React.Component<RouteComponentProps> {

    componentWillMount = () => stateStore.dispatch(setHeaderTitle("Posts"))

    componentDidMount = () => this.fetchItems()

    componentDidUpdate(prevProps: RouteComponentProps) {
        if (this.props.location.search !== prevProps.location.search) {
          this.fetchItems()
        }
      }
    
    render = () => <PagedList {...this.state as PagedListProps}/>

    private fetchItems = () => 
        fetch(`${serviceBaseUrl}/posts${this.props.location.search}`)
            .then(res => res.json())
            .then(data => this.setState({...this.state, 
                items: this.toItems(data.resources), 
                paging: data.paging
            }))
            .catch(console.log)

    private toItems = (items:any[]): Item[] =>
        items.map((item:any) => ({
            content: item.content,
            heading: item.userNickname,
            date: item.createdAt
        }))
        
}
