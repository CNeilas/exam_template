import { ProductCard } from '../components/ProductCard';

export const Homepage = () => {
  const productList = [1, 2, 3, 4, 5];
  const tempProduct = {
    name: 'Product A',
    description: 'Lorem Lorem Lorem Lorem Lorem LoremLorem Lorem LoremLorem Lorem LoremLorem Lorem Lorem',
  };
  return (
    <div>
      Hello
      <section className="products">
        {productList.map((product) => {
          return <ProductCard key={product} product={tempProduct} />;
        })}
      </section>
    </div>
  );
};
