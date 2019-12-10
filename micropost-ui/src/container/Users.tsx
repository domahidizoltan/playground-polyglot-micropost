import React from "react";
import { PagedList } from "../component";
import { stateStore, setHeaderTitle } from "../statestore/AppState";
import { PagedListProps, Item } from "../component/PagedList";
import serviceBaseUrl from "../common/Constants";
import { RouteComponentProps } from "react-router";
import { Helper } from "../common/Helpers";

export default class Users extends React.Component<RouteComponentProps> {
    componentWillMount = () => stateStore.dispatch(setHeaderTitle("Users"))

    componentDidMount = () => this.fetchItems()

    componentDidUpdate(prevProps: RouteComponentProps) {
        if (this.props.location.search !== prevProps.location.search) {
          this.fetchItems()
        }
      }

    render = () => <PagedList {...this.state as PagedListProps}/>

    private fetchItems = () => 
        fetch(`${serviceBaseUrl}/users${this.props.location.search}`)
            .then(res => res.json())
            .then(data => this.setState({...this.state, 
                items: this.toItems(data.resources), 
                paging: data.paging
            }))
            .catch(console.log)

    private toItems = (items:any[]): Item[] =>
        items.map((item:any) => ({
            content: item.nickname,
            url: Helper.getLinksAsMap(item).get('read')
        }))
}
