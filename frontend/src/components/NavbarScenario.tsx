import {
    Collapse,
    DropdownItem,
    DropdownMenu,
    DropdownToggle,
    Nav,
    Navbar,
    NavbarBrand,
    NavbarToggler,
    UncontrolledDropdown
} from "reactstrap";
import {useNavigate} from "react-router-dom";

export const NavbarScenario = () => {
    const navigate = useNavigate();
    return (
        <Navbar
            color="dark"
            expand="md"
            dark
        >
            <NavbarBrand onClick={() => navigate("/")} className="navbar-brand">
                HSZG
            </NavbarBrand>
            <NavbarToggler onClick={function noRefCheck() {
            }}/>
            <Collapse navbar>
                <Nav
                    className="me-auto"
                    navbar
                >
                </Nav>
                <UncontrolledDropdown
                    inNavbar
                >
                    <DropdownToggle
                        caret
                    >
                        Options
                    </DropdownToggle>
                    <DropdownMenu left>
                        <DropdownItem>
                            Option 1
                        </DropdownItem>
                        <DropdownItem>
                            Option 2
                        </DropdownItem>
                        <DropdownItem divider/>
                        <DropdownItem>
                            Reset
                        </DropdownItem>
                    </DropdownMenu>
                </UncontrolledDropdown>
            </Collapse>
        </Navbar>
    );
}