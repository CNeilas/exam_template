export const ProductCard = (product) => {
  const {id, name, description, price, city} = product
  return (
    <div className="card">
      <h3 className="card__title">{product.name}</h3>
      <p>{product.description}</p>
      <p>{product.price}</p>
      <p>{product.city}</p>
    </div>
  );
};
