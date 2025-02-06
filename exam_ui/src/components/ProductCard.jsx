export const ProductCard = ({ product }) => {
  return (
    <div className="card">
      <h3 className="card__title">{product.name}</h3>
      <p>{product.description}</p>
    </div>
  );
};
