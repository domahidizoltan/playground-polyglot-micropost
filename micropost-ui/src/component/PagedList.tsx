import React from 'react';
import './PagedList.css'
import { RouteComponentProps, withRouter } from 'react-router';
import { Navigation } from '../common/Navigation';

export interface Item {
    title: string,
    date?: string,
    description?: string
}

interface PagingLink {
    rel: string,
    href: string
}

interface Paging {
    links: PagingLink[],
    page: number,
    totalPages: number
}

export interface PagedListProps extends RouteComponentProps {
    paging: Paging,
    items: Item[]
}

class PagedList extends React.Component<PagedListProps> {
    render() {
        return (
        <div>
            <div className="list-group striped">
                {this.getItemList(this.props.items)}
            </div>

            {this.getPagination(this.props.paging)}
        </div>
        )
    }

    private getItemList(items: Item[]) {
        if (items) {
            if (items.length > 0) {
                return items.map(item => (
                    <div className="list-group-item">
                        <div className="d-flex w-100 justify-content-between">
                            <b>{item.title}</b>
                            <small>{item.date || ''}</small>
                        </div>
                        {(item.description) ? <p>{item.description}</p> : ''}
                    </div>
                ))
            } else {
                return <div className="list-group-item d-flex justify-content-center">No content</div>
            }
        } else {
            return (
                <div className="list-group-item d-flex justify-content-center">
                    <span className="spinner-grow spinner-grow-sm"></span> Loading...
                </div>
            )
        }
    }

    private getPagination(paging: Paging) {
        if (paging) {
            let pageLinks = new Map(paging.links.map(link => [link.rel, link.href] as [string, string]))

            return (
                <nav>
                    <ul className="pagination d-flex w-100 justify-content-between">
                        <div>
                            {this.getPageLink('double-left', pageLinks.get('first'))}
                            {this.getPageLink('left', pageLinks.get('prev'))}
                        </div>
                        <div className="page-item align-middle">
                            {paging.page} / {paging.totalPages}
                        </div>
                        <div>
                            {this.getPageLink('right', pageLinks.get('next'))}
                            {this.getPageLink('double-right', pageLinks.get('last'))}
                        </div>
                    </ul>
                </nav>
            )
        }
    }

    private getPageLink(className: string, link?: string) {
        if (link) {
            let pageClass = 'page-item page-link fa fa-angle-' + className
            return <a className={pageClass} onClick={e => this.navTo(link)}></a>
        }
    }

    private navTo = (url: string) => {
        Navigation.navTo(url, this.props)
    }
}

export default withRouter(PagedList)