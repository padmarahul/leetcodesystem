import styled from "styled-components";

export const FeaturesContainer = styled.div`
  z-index: 1;
  width: 100%;
  margin-right: auto;
  margin-left: auto;
  padding-left: 20px;
  padding-right: 20px;

  @media screen and (max-width: 991px) {
    padding-left: 50px;
    padding-right: 50px;
    height: 450px;
  }
`;
export const StyledTable = styled.table`
  width: 125%;
  border-collapse: collapse;
  margin-left: auto;
  margin-right: auto;
  font-size: 0.9em;
  font-family: sans-serif;
  min-width: 400px;
  box-shadow: 0 0 20px rgba(0, 0, 0, 0.15);

  thead tr {
    background-color: #009879;
    color: #ffffff;
    text-align: left;
  }
  thead tr th {
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
  }

  th, td {
    padding: 12px 15px;
    color: #333; 
  }

  tbody tr {
    border-bottom: 1px solid #dddddd;
    background-color: #ffffff;
  }

  tbody tr:nth-of-type(even) {
    background-color: #f3f3f3;
  }

 
  tbody tr:hover {
    background-color: #f1f1f1;
  }
`;


export const FeaturesSec = styled.div`
  color: #fff;
  padding: 5vh;
  min-height: 100vh;
  display:flex;
  flex-direction:column;
  justify-content:centre;
  background: ${({ lightBg }) => (lightBg ? "#fff" : "#101522")};
`;

export const FeaturesRow = styled.div`
  display: flex;
  flex-wrap: wrap;
  flex-direction: ${({ imgStart }) => (imgStart ? "row-reverse" : "row")};
`;

export const FeaturesColumn = styled.div`
  margin-bottom: 15px;
  padding-right: 15px;
  padding-left: 15px;
  flex: 1;
  max-width: 50%;
  flex-basis: 50%;

  @media screen and (max-width: 768px) {
    max-width: 100%;
    flex-basis: 100%;
    display: flex;
    justify-content: center;
  }
`;

export const TopLine = styled.div`
  color: ${({ lightTopLine }) => (lightTopLine ? "#a9b3c1" : "#4B59F7")};
  font-size: 6vh;
  /* line-height: 16px; */
  font-weight: 700;
  letter-spacing: 1.4px;
  margin: 1vh 0 1vh 0;
`;

export const TextWrapper = styled.div`
  max-width: 540px;
  padding-top: 2vh;
  margin-left:-50px;
  padding-bottom: 60px;

  @media screen and (max-width: 768px) {
    padding-bottom: 65px;
  }
`;

export const ImgWrapper = styled.div`
  display: flex;
  max-width: 550px;
  margin-left: 200px;
  justify-content: ${({ start }) => (start ? "flex-start" : "flex-end")};
`;

export const Img = styled.img`
  border: 0;
  width: 50vh;
  vertical-align: middle;
  display: inline-block;
`;

export const Table = styled.table`
  border:0;
margin-top:150px;
margin-left:-300px
`;
