import React, {useEffect, useState} from "react";
import { animateScroll as scroll } from "react-scroll";
import {
  Nav,
  NavbarContainer,
  NavLogo,
  NavMenu,
  NavItem,
  NavLinks,
  MobileIcon
} from "./NavbarElements";
import { FaBars } from "react-icons/fa";
import { useNavigate } from "react-router-dom";
import LogoTM from "../../common/assets/untlogo.png";


const Navbar = ({ toggle }) => {
  const navigate=useNavigate();
  const [scrollNav, setScrollNav] = useState(false)
  const changeNav = () => {
    if(window.scrollY >= 80){
      setScrollNav(true)
    }
    else{
      setScrollNav(false)
    }
  }

  useEffect(() => {
    window.addEventListener('scroll', changeNav)
  },[]);

  const toggleHome = () => {
    scroll.scrollToTop()
  }

  const handleLoginClick=(e)=>
  {
    e.preventDefault()
    navigate("/login");
  }

  const handleSignUpClick=(e)=>
  {
    e.preventDefault()
    navigate("/signup");
  }

  return (
    <>
      <Nav scrollNav={scrollNav}>
        <NavbarContainer>
          <NavLogo onClick={toggleHome} to="/">
           <h1>
          LEET CODE
           </h1>
          </NavLogo>
          <MobileIcon onClick={toggle}>
            <FaBars />
          </MobileIcon>
          <NavMenu>
            <NavItem>
              <NavLinks to="smartContract" smooth={true} duration={500} spy={true} exact='true' offset={-80} >About</NavLinks>
            </NavItem>
            <NavItem>
            <NavLinks  onClick={event => handleLoginClick(event)}smooth={true} duration={500} spy={true} exact='true' offset={-80} >Login</NavLinks>
            </NavItem>
            <NavItem>
              <NavLinks  onClick={event => handleSignUpClick(event)}smooth={true} duration={500} spy={true} exact='true' offset={-80} >Sign Up</NavLinks>
             </NavItem>
          </NavMenu>
          <NavLogo onClick={toggleHome} to="/">
            <img style={{background:"white"}} src={LogoTM} alt="" /> 
          </NavLogo>
        </NavbarContainer>
      </Nav>
    </>
  );
};

export default Navbar;
