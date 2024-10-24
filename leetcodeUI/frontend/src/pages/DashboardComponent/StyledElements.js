import styled from "styled-components";

// Filter container
export const FilterContainer = styled.div`
  display: flex;
  justify-content: space-between;
  margin-bottom: 20px;
`;

export const FilterLabel = styled.label`
  font-size: 16px;
  font-weight: bold;
  margin-right: 10px;
`;

export const FilterSelect = styled.select`
  padding: 5px;
  font-size: 14px;
`;

// Table container
export const TableContainer = styled.div`
  width: 100%;
  overflow-x: auto;
  margin-top: 80px;
`;

export const Table = styled.table`
  width: 100%;
  border-collapse: collapse;
  background-color: #f8f9fa;
`;

export const TableHead = styled.thead`
  background-color: #343a40;
  color: black;
`;

export const TableRow = styled.tr`
  background-color: ${({ index }) => (index % 2 === 0 ? "#f1f1f1" : "#e9ecef")};
  transition: background-color 0.3s ease, font-weight 0.3s ease;
  
  &:hover {
    background-color: #d4edda;
    font-weight: bold;
    cursor: pointer;
  }
`;

export const TableHeader = styled.th`
  padding: 12px 16px;
  text-align: left;
  font-size: 16px;
  font-weight: bold;
  border-bottom: 2px solid #dee2e6;
`;

export const TableData = styled.td`
  padding: 12px 16px;
  border-bottom: 1px solid #dee2e6;
`;

export const DifficultyLabel = styled.span`
  font-size: 14px;
  padding: 4px 8px;
  background-color: ${({ difficulty }) => {
    if (difficulty === "easy") return "#28a745"; // Green
    if (difficulty === "medium") return "#ffc107"; // Yellow
    if (difficulty === "hard") return "#dc3545"; // Red
    return "#6c757d"; // Default color for other cases
  }};
  color: white;
  border-radius: 4px;
  text-transform: capitalize;
`;
